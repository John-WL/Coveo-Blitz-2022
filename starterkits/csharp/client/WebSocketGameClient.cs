using System;
using System.Collections.Generic;
using System.Net.WebSockets;
using System.Threading;
using System.Threading.Tasks;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using Newtonsoft.Json.Converters;

namespace Blitz2022
{
    public class WebSocketGameClient
    {
        private static string address = "127.0.0.1:8765";
        private Solver solver;
        public WebSocketGameClient(Solver solver)
        {
            this.solver = solver;
        }

        public async Task run()
        {
            using (ClientWebSocket webSocket = new ClientWebSocket())
            {
                Uri serverUri = new Uri("ws://" + address);
                await webSocket.ConnectAsync(serverUri, CancellationToken.None);

                string token = Environment.GetEnvironmentVariable("TOKEN");
                string registerPayload = JsonConvert.SerializeObject(new { type = "REGISTER", token = token });


                await webSocket.SendAsync(
                    new ArraySegment<byte>(Encoding.UTF8.GetBytes(registerPayload)),
                    WebSocketMessageType.Text, true, CancellationToken.None);

                while (webSocket.State == WebSocketState.Open)
                {

                    string rawMessage = await readMessage(webSocket);
                    Dictionary<string, dynamic> message = JsonConvert.DeserializeObject<Dictionary<string, dynamic>>(rawMessage);
                    if ("ERROR".Equals(message["type"]))
                    {
                        Console.Error.WriteLine(rawMessage);
                        return;
                    }

                    GameMessage gameMessage = JsonConvert.DeserializeObject<GameMessage>(rawMessage);
                    if (gameMessage != null)
                    {

                        Answer command = solver.getAnswer(gameMessage);
                        string serializedCommand = JsonConvert.SerializeObject(new { type = "COMMAND", actions = command, tick = gameMessage.tick });

                        await webSocket.SendAsync(
                            Encoding.UTF8.GetBytes(serializedCommand),
                            WebSocketMessageType.Text,
                            true, CancellationToken.None);
                    }

                }
            }
        }
        public async static Task<string> readMessage(ClientWebSocket client)
        {
            string result = "";

            WebSocketReceiveResult receiveResult;
            do
            {
                ArraySegment<byte> segment = new ArraySegment<byte>(new byte[1024]);
                receiveResult = await client.ReceiveAsync(segment, CancellationToken.None);
                result += Encoding.UTF8.GetString(segment.Array);
            } while (!receiveResult.EndOfMessage);


            return result;
        }
    }
}
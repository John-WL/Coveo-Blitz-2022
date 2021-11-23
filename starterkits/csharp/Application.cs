using System;
using System.Threading.Tasks;
using Blitz2022;
public static class Application
{
    public static void Main(string[] args)
    {
        Solver solver = new Solver();
        string token = Environment.GetEnvironmentVariable("TOKEN");
        Task task = !String.IsNullOrWhiteSpace(token) ? new WebSocketGameClient(solver).run() : new LocalGameClient(solver).run();
        task.Wait();
    }
}
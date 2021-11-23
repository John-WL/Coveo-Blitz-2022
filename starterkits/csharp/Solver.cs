using System;
using System.Collections.Generic;
using Newtonsoft.Json;

namespace Blitz2022
{
    public class Solver
    {
        public Solver()
        {
            // This method should be use to initialize some variables you will need throughout the challenge.
        }

        /*
        * Here is where the magic happens, for now the answer is empty. I bet you can do better ;)
        *
        */
        public Answer getAnswer(GameMessage gameMessage)
        {
            Question question = gameMessage.payload;
            Console.WriteLine("Received Question: {0}", JsonConvert.SerializeObject(question));

            IList<TotemAnswer> totems = new List<TotemAnswer> {
                new TotemAnswer {
                    shape = Totem.I,
                    coordinates = new List<CoordinatePair> {
                        (0,0),
                        (1,0),
                        (2,0),
                        (3,0)
                    }
                }
            };

            Answer answer = new Answer
            {
                totems = totems
            };
            Console.WriteLine("Sending Answer: {0}", JsonConvert.SerializeObject(answer));
            return answer;
        }
    }
}
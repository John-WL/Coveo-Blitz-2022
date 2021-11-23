using System.Collections.Generic;
using System.Threading.Tasks;


namespace Blitz2022
{
    public class LocalGameClient
    {
        private Solver solver;
        public LocalGameClient(Solver solver)
        {
            this.solver = solver;
        }

        public async Task run()
        {
            await Task.Run(() =>
            {
                GameMessage sample = new GameMessage
                {
                    tick = 1,
                    payload = new Question
                    {
                        totems = new List<TotemQuestion> {
                        new TotemQuestion{
                            shape = Totem.I
                        }
                    }
                    }
                };

                solver.getAnswer(sample);
            });
        }
    }
}
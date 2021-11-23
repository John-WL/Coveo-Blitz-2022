use crate::solver::Solver;
use crate::game_interface::{GameMessage, Question, Totem, TotemQuestion};

pub struct LocalGameClient {
    solver: Solver,
}

impl LocalGameClient {
    pub fn new(solver: Solver) -> Self {
        LocalGameClient { solver }
    }

    pub async fn run(&self) {
        println!("[Running in local mode]");

        let totem_question = TotemQuestion { shape: Totem::I };
        let question = Question { totems: vec![totem_question] };
        let game_message = GameMessage { tick: 1, payload: question };

        self.solver.get_answer(&game_message).expect("There was an error in the solver's code!");
    }
}

use std::error::Error;
use crate::game_interface::{Answer, GameMessage, Totem, TotemAnswer};

pub struct Solver {}

impl Solver {
    /// Initialize your solver
    ///
    /// This method should be used to initialize some
    /// variables you will need throughout the challenge.
    pub fn new() -> Self {
        Solver {}
    }

    /// Answer the question
    ///
    /// This is where the magic happens, for now the
    /// answer is a single 'I'. I bet you can do better ;)
    pub fn get_answer(&self, game_message: &GameMessage) -> Result<Answer, Box<dyn Error>> {
        let question = &game_message.payload;
        println!("Received Question: {:?}", question);

        let coordinates = vec![(0, 0), (1, 0), (2, 0), (3, 0)];
        let totems = vec![TotemAnswer::new(Totem::I, coordinates)];
        let answer = Answer::new(totems);

        println!("Sending Answer: {:?}", answer);

        Ok(answer)
    }
}

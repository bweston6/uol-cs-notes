-- The Assignment 3 checker.
--
-- Please read the instructions in the handout carefully before running this
-- checker.
--
-- Note that you *MUST* change the directory before loading this file in, or
-- ghci will not be able to find Assignment3.hs. The instructions in the handout
-- tell you how to do this.


{-# LANGUAGE ExistentialQuantification #-}

module Checker3 where

import System.Timeout
import Control.Exception
import Data.Maybe

import Main


---- Building test cases

data Test = forall a b. (Show b, Eq b) => Test
    {
        function :: a -> b,
        argument :: a,
        expected :: b,
        display  :: String
    }

oneArg :: (Show a, Show b, Eq b) => (a -> b) -> a -> b -> String -> Test
oneArg func arg exp name = Test 
    {
        function = func,
        argument = arg,
        expected = exp,
        display  = unwords [name, show arg]
    }

twoArg :: (Show a, Show b, Show c, Eq c) => 
            (a -> b -> c) -> a -> b -> c -> String -> Test
twoArg func arg1 arg2 exp name = Test 
    {
        function = uncurry func,
        argument = (arg1, arg2),
        expected = exp,
        display  = unwords [name, show arg1, show arg2]
    }


---- Running test cases

data Result = Terminated { output  :: String, correct :: Bool }
            | Exception  { message :: String }
            | Timeout


runFunc :: Test -> Result
runFunc (Test func arg exp disp) = 
        Terminated { output = show out, correct = out == exp}
    where out = func arg

handler :: SomeException -> IO Result
handler e = return $ case show e of "<<timeout>>" -> Timeout
                                    _             -> Exception $ takeWhile (/='\n') $ show e

runTest :: Test -> IO Result
runTest test = do
    let tester = do
        let out = runFunc test
        evaluate $ correct out -- this will force all exceptions
        return out
    res <- timeout 1000000 $ tester `catch` handler
    return $ fromMaybe Timeout res


---- Displaying results

expectedOutput :: Test -> String
expectedOutput (Test _ _ exp _) = show exp

showResult :: (Test, Result) -> String
showResult (test, result) = 
    let
        called = "called: " ++ display test
        expected = "expected: " ++ expectedOutput test
        actual = "output:   " ++ case result of 
            (Terminated out correct) -> 
                out ++ " (" ++ (if correct then "correct" else "WRONG") ++ ")"
            (Exception msg) -> "Exception (" ++ msg ++ ")"
            Timeout -> "Timeout"
    in
        called ++ "\n    " ++  expected ++ "\n    " ++ actual ++ "\n"

passed :: Result -> Bool
passed (Terminated _ c) = c
passed (Exception _ )   = False
passed Timeout          = False

passedStats :: [Result] -> String
passedStats results = "passed: " ++ pass ++ ", failed: " ++ fail
    where passCount = length $ filter passed results
          pass = show passCount
          fail = show $ length results - passCount

compileCheck :: IO ()
compileCheck = 
    putStrLn "Your file compiles successfully and has correct types"


test :: IO ()
test = do
    results <- mapM runTest testCases
    mapM_ (putStrLn . showResult) $ zip testCases results 
    putStrLn $ passedStats results
    putStrLn "\nNOTE: Passing these tests does not guarantee any marks."
    putStrLn "      You are advised to test thoroughly with your own inputs."


---- The test cases

maze2 :: [String]
maze2  = ["#####","#   #","# # #","# # #","#####"]

maze2' :: [String]
maze2' = ["#####","#@  #","# # #","# # #","#####"]

testCases :: [Test]
testCases = 
    [ oneArg (Main.is_wall maze2) (0 :: Int, 0 :: Int) True  "is_wall maze2"
    , oneArg (Main.place_player maze2) (1 :: Int, 1 :: Int) maze2' "place_player maze2"
    , twoArg Main.move (1 :: Int, 1 :: Int) 'w' (1 :: Int, 0 :: Int) "move"
    , twoArg (Main.can_move maze2) (1 :: Int, 1 :: Int) 's' True "can_move maze2"
    , twoArg (Main.get_path maze2) (1 :: Int, 1 :: Int) (3 :: Int, 3 :: Int) [(1 :: Int,1 :: Int),(2,1),(3,1),(3,2),(3,3)] "get_path maze2"
    ]

---- Check that the IO actions have the correct types

get_maze_check :: String -> IO [String]
get_maze_check = Main.get_maze

print_maze_check :: [String] -> IO ()
print_maze_check = Main.print_maze

game_loop_check :: [String] -> (Int, Int) -> IO ()
game_loop_check = Main.game_loop

main_check :: IO ()
main_check = Main.main

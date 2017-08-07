(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))

(describe "print-game-menu"
          (it "prints game menu"
              (should-contain "Please select the type of game:\n1. Human vs Human\n2. Human vs Computer"
                              (with-out-str (print-game-menu ["1. Human vs Human" "2. Human vs Computer"])))))

(describe "print-board"
          (it "draws board"
              (should-contain "| X | 2 | 3 |\n\t| O | 5 | X |\n\t| 7 | 8 | 9 |\n" 
                       (with-out-str (print-board  
                                           [
                                            :X :_ :_ 
                                            :O :_ :X
                                            :_ :_ :_ ])))))

(describe "print-turn-message"
          (it "prints message"
              (should-contain "X's turn" (with-out-str (print-turn-message :X)))))

(describe "print-computer-move"
          (it "prints message"
              (should-contain "Computer chose space 5" (with-out-str (print-computer-move 5)))))

(describe "print-error"
          (it "prints message"
              (should= "my message\n" (with-out-str (print-error "my message")))))

(describe "print-marker-prompt"
  (it "prints message"
    (should-contain "Player 1, please enter marker:" (with-out-str (with-in-str
                   " 9\t" (print-marker-prompt 1))))))

(describe "print-move-prompt"
  (it "prints message"
    (should-contain "Please enter move:" (with-out-str (with-in-str
                   " 9\t" (print-move-prompt))))))

(describe "declare-winner"
          (it "prints winning message to the console" (should= "Player X wins!\n" (with-out-str (declare-winner "X")))))

(describe "declare-draw"
          (it "prints draw message to the console", (should= "Cats game.\n" (with-out-str (declare-draw)))))



(describe "get-user-input"
          (it "gets and trims input"
              (should= "9" (with-in-str " 9 \t" (get-user-input)))))

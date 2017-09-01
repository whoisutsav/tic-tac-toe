(ns tic-tac-toe.console-ui-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-ui :refer :all]))

(describe "Game Setup"
  (describe "print-menu"
          (it "prints menu"
              (should-contain "Please select the type of game:\n1. Human vs Human\n2. Human vs Computer"
                              (with-out-str (print-menu ["1. Human vs Human" "2. Human vs Computer"])))))

  (describe "print-marker-prompt"
        (it "prints message"
            (should-contain "Please enter player's marker:" (with-out-str (with-in-str
                   " 9\t" (print-marker-prompt false)))))))

(describe "Turn"
  (describe "print-move-prompt"
        (it "prints message"
            (should-contain "Please enter move:" (with-out-str (with-in-str
                   " 9\t" (print-move-prompt))))))

  (describe "print-turn"
          (it "prints message"
              (should-contain "X's turn" (with-out-str (print-turn-prompt :X)))))

  (describe "print-move"
          (it "prints message"
              (should-contain "X moves to 5" (with-out-str (print-move :X 5))))))




(describe "Decision"
  (describe "declare-winner"
          (it "prints winning message to the console" (should= "X wins!\n" (with-out-str (declare-winner "X")))))

  (describe "declare-draw"
          (it "prints draw message to the console", (should= "Cats game.\n" (with-out-str (declare-draw))))))




(describe "General"
  (describe "print-message"
          (it "prints message"
              (should= "my message\n" (with-out-str (print-message "my message")))))

  (describe "get-user-input"
          (it "gets and trims input"
            (should= "9" (with-in-str " 9 \t" (get-user-input))))))

(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))

(describe "print-board"
          (it "draws board"
              (should-contain "X | 2 | 3\nO | 5 | X\n7 | 8 | 9\n" 
                       (with-out-str (print-board  
                                           [
                                            :X :_ :_ 
                                            :O :_ :X
                                            :_ :_ :_ ])))))

(describe "print-computer-move"
          (it "prints message"
              (should-contain "Computer chose space 5" (with-out-str (print-computer-move 5)))))

(describe "show-error"
          (it "prints message to the console"
              (should= "my message\n" (with-out-str (show-error "my message")))))

(describe "declare-winner"
          (it "prints winning message to the console" (should= "Player X wins!\n" (with-out-str (declare-winner "X")))))

(describe "declare-draw"
          (it "prints draw message to the console", (should= "Cats game\n" (with-out-str (declare-draw)))))

(describe "prompt-for-move"
  (it "prints prompt"
    (should-contain "X, please enter move:" (with-out-str (with-in-str
                   " 9\t" (prompt-for-move "X" []))))))


(describe "get-user-input"
          (it "gets and trims input"
              (should= "9" (with-in-str " 9 \t" (get-user-input)))))

(ns tic-tac-toe.console-output-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-output :refer :all]))

(describe "print-board"
          (it "draws board"
              (should= "X\t_\t_\nO\t_\tX\n_\t_\t_\n" 
                       (with-out-str (print-board {:x "X" :o "O"} 
                                           [
                                            :x :_ :_ 
                                            :o :_ :x
                                            :_ :_ :_ ])))))


(describe "show-error"
          (it "prints message to the console"
              (should= "my message\n" (with-out-str (show-error "my message")))))

(describe "declare-winner"
          (it "prints winning message to the console" (should= "Player X wins!\n" (with-out-str (declare-winner "X")))))

(describe "declare-draw"
          (it "prints draw message to the console", (should= "Cats game\n" (with-out-str (declare-draw)))))

(describe "show-move-prompt"
  (it "prints prompt"
    (should-contain "X: please enter move" (with-out-str (with-in-str
                   " 9\t" (show-move-prompt "X" {:x "X" :o "O"} []))))))


(describe "get-user-input"
          (it "gets and trims input"
              (should= "9" (with-in-str " 9 \t" (get-user-input)))))

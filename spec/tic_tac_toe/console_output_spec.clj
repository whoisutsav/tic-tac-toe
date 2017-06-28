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


(describe "print-message"
          (it "prints message to the console"
              (should= "my message\n" (with-out-str (print-message "my message")))))

(describe "print-winner"
          (it "prints winning message to the console" (should= "Player X wins!\n" (with-out-str (print-winner "X")))))

(describe "print-draw"
          (it "prints draw message to the console", (should= "Cats game\n" (with-out-str (print-draw)))))

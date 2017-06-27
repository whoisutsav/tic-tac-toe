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

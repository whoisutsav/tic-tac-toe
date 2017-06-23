(ns tic-tac-toe.view-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.view :refer :all]))

(describe "draw"
          (it "draws board"
              (should= "X\t_\t_\nO\t_\tX\n_\t_\t_\n" 
                       (with-out-str (draw "X" "O" [
                                                    :1 :_ :_ 
                                                    :2 :_ :1
                                                    :_ :_ :_ ])))))

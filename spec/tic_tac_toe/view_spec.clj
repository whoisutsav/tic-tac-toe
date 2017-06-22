(ns tic-tac-toe.view-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.view :refer :all]))

(describe "draw"
          (it "draws board"
              (should= "X\t_\n_\tO\n" (with-out-str (draw "X" "O" [:1 :_ :_ :2])))))

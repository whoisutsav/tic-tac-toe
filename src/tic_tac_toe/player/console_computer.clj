(ns tic-tac-toe.player.console-computer
  (:require [tic-tac-toe.player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-interface :as console]))

(defmethod get-move :computer [board _]
  (let [move (rand-nth (board/get-empty-spaces board))]
    (console/print-computer-move move)
    move))

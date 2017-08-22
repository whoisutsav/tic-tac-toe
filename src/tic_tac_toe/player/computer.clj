(ns tic-tac-toe.player.computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-ui :as console-ui]))

(defmethod get-move :computer [board _ _]
  (let [move (rand-nth (board/get-empty-spaces board))]
    (console-ui/print-computer-move move)
    move))

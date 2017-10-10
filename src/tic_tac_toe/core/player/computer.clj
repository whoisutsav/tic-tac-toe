(ns tic-tac-toe.core.player.computer
  (:require [tic-tac-toe.core.player :refer [get-move]]
            [tic-tac-toe.core.board :refer [empty-spaces]]))

(defmethod get-move :computer [game]
  (-> (:board game)
      (empty-spaces)
      (rand-nth)))

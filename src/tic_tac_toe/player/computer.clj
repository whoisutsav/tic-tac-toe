(ns tic-tac-toe.player.computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.board :refer [empty-spaces]]))

(defmethod get-move :computer [game]
  (-> (:board game)
      (empty-spaces)
      (rand-nth)))

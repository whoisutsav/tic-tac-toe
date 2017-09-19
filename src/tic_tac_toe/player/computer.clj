(ns tic-tac-toe.player.computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.board :as board]))

(defmethod get-move :computer [game]
  (rand-nth (board/get-empty-spaces (:board game))))

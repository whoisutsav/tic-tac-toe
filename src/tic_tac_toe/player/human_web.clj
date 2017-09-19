(ns tic-tac-toe.player.human-web
  (:require [tic-tac-toe.player :refer [get-move]]))


(defmethod get-move :human-web [game]
  (:move game))

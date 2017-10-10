(ns tic-tac-toe.web.human-web
  (:require [tic-tac-toe.core.player :refer [get-move]]))


(defmethod get-move :human-web [game]
  (:move game))

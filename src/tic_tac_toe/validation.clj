(ns tic-tac-toe.validation
   (:require [tic-tac-toe.board :as board]
             [tic-tac-toe.rules :as rules]))

(defn move [board move]
  (cond
    (not (rules/numeric? move)) {:error "Move must be a number"}
    (not (rules/on-board? board (read-string move))) {:error "Move must be on board"}
    (not (rules/available? board (read-string move))) {:error "Space is occupied"}
    :else {:error nil})) 


(defn marker [marker taken-marker]
  (cond
    (not (rules/single-letter? marker)) {:error "Marker must be a single letter"}
    (not (rules/marker-available? marker taken-marker)) {:error "Marker taken. Please choose a different marker"}
    :else {:error nil}))

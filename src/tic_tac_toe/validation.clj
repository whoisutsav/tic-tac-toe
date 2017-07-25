(ns tic-tac-toe.validation
   (:require [tic-tac-toe.board :as board]
             [tic-tac-toe.validation-predicates :as predicates]))

(defn move [board move]
  (cond
    (not (predicates/numeric? move)) {:error "Move must be a number"}
    (not (predicates/on-board? board (read-string move))) {:error "Move must be between 1 and size of board"}
    (not (predicates/available? board (read-string move))) {:error "Space is occupied"}
    :else {:error nil})) 


(defn marker [marker]
  (cond
    (not (predicates/single-letter? marker)) {:error "Marker must be a single letter"}
    :else {:error nil}))

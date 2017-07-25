(ns tic-tac-toe.validation
   (:require [tic-tac-toe.board :as board]
             [tic-tac-toe.validation-helper :as helper]))

(defn move [board move]
  (cond
    (not (helper/numeric? move)) {:error "Move must be a number"}
    (not (helper/on-board? board (read-string move))) {:error "Move must be between 1 and size of board"}
    (not (helper/available? board (read-string move))) {:error "Space is occupied"}
    :else {:error nil})) 


(defn marker [marker]
  (cond
    (not (helper/single-letter? marker)) {:error "Marker must be a single letter"}
    :else {:error nil}))

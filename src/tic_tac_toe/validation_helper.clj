(ns tic-tac-toe.validation-helper
  (:require [tic-tac-toe.board :as board]))

(defn numeric? [s]
  (not (nil? (re-matches #"^\d+$" s))))

(defn on-board? [board move]
  (<= 1 move (board/size board)))

(defn available? [board move]
  (nil? (board/bget move board)))

(defn single-letter? [s]
  (boolean (re-matches #"^[a-z|A-Z]{1}" s)))

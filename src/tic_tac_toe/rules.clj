(ns tic-tac-toe.rules
  (:require [tic-tac-toe.board :as board]))

(defn numeric? [s]
  (not (nil? (re-matches #"^\d+$" s))))

(defn single-letter? [s]
  (boolean (re-matches #"^[a-z|A-Z]{1}" s)))

(defn on-board? [board move]
  (<= 1 move (board/size board)))

(defn available? [board move]
  (nil? (board/get-marker move board)))

(defn marker-available? [marker taken-marker]
  (not= marker taken-marker))

(ns tic-tac-toe.validation
   (:require [tic-tac-toe.board :as board]))

(def move-errors 
  {
    :not-numeric "Move must be a number"
    :not-on-board "Move must be between 0 and size of board"
    :not-available "Space is occupied" })

(def marker-errors
  {
   :not-single-letter "Move must be a single letter" })

(defn- numeric? [s]
  (not (nil? (re-matches #"^\d+$" s))))

(defn- on-board? [board move]
  (<= 0 move (dec (count board))))

(defn- available? [board move]
  (board/empty-space? move board))

(defn move [board move]
  (cond
    (not (numeric? move)) {:error (move-errors :not-numeric)}
    (not (on-board? board (read-string move))) {:error (move-errors :not-on-board)}
    (not (available? board (read-string move))) {:error (move-errors :not-available)}
    :else {:error nil})) 

(defn single-letter? [s]
  (re-matches #"^[a-z|A-Z]{1}" s))

(defn marker [marker]
  (cond
    (not (single-letter? marker)) {:error (marker-errors :not-single-letter)}
    :else {:error nil}))

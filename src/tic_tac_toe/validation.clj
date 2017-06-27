(ns tic-tac-toe.validation
   (:require [tic-tac-toe.board :as board]))

(def error-messages 
  {
    :not-numeric "Move must be a number"
    :not-on-board "Move must be between 0 and size of board"
    :not-available "Space is occupied" })

(defn- numeric? [s]
  (not (nil? (re-matches #"^\d+$" s))))

(defn- on-board? [board move]
  (<= 0 move (count board)))

(defn- available? [board move]
  (board/empty-space? move board))

(defn move [board move]
  (cond
    (not (numeric? move)) {:error (error-messages :not-numeric)}
    (not (on-board? board (read-string move))) {:error (error-messages :not-on-board)}
    (not (available? board (read-string move))) {:error (error-messages :not-available)}
    :else {:error nil})) 

(ns tic-tac-toe.move
  (:require [tic-tac-toe.view :as view]
            [tic-tac-toe.helper :as helper]
            [tic-tac-toe.board :as board]
            [clojure.string :as str]))

(defn- prompt-move []
  (str/trim (helper/prompt "Please enter move: ")))

(defn- is-number? [s]
  (not (nil? (re-matches #"^\d+$" s))))

(defn- is-in-range? [board move]
  (contains? (set (range 0 (count board))) move))

(defn- is-empty? [board move]
  (not (board/is-occupied? board move)))

(defn- get-errors [board move]
  (cond
    (not (is-number? move)) {:error "Move must be a number"}
    (not (is-in-range? board (read-string move))) {:error "Move must be between 1 and size of board"}
    (not (is-empty? board (read-string move))) {:error "Space is occupied"}
    :else {:error nil})) 

(defn get-move [board]
  (loop [move (prompt-move)] 
    (if-let [error-str (:error (get-errors board move))] 
      (do (println error-str) 
          (recur (prompt-move)))
      (read-string move))))



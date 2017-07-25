(ns tic-tac-toe.console 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-space "_")

;TODO name this something else, simplify
(defn- populate-spaces [board]
      (map #(if (nil? (board/bget % board)) % (name (board/bget % board))) (range 1 (inc (board/size board)))))

(defn print-board2 [board]
  (loop [rows (partition 3 (populate-spaces board))]
    (when-let [row (first rows)] 
      (println (str "\t| " (reduce str "" (interpose " | " row)) " |"))
      (recur (rest rows)))))

(defn print-board [board]
  (println)
  (println (apply str "\t" (repeat 13 "-")))
  (print-board2 board)
  (println (apply str "\t" (repeat 13 "-")))
  (println))

(defn print-turn-message [marker]
  (println (str (name marker) "'s turn")))

(defn print-computer-move [move]
  (println (str "Computer chose space " move)))

(defn show-error [message]
  (println message))

(defn declare-winner [marker]
  (println (str "Player " (name marker) " wins!")))

(defn declare-draw []
  (println "Cats game."))

(defn get-user-input []
  (str/trim (read-line)))

(defn prompt-for-move []
  (println (str "Please enter move:"))
  (get-user-input))

(defn show-marker-prompt [player-num]
  (println (str "Player " player-num ", please enter marker:")))


(ns tic-tac-toe.console 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-marker "_")

(defn- populate-markers [players board]
      (map #(get (get players % ) :marker empty-marker) board))

(defn print-board [players board]
  (loop [rows (partition 3 (populate-markers players board))]
    (when-let [row (first rows)] 
      (println (reduce str "" (interpose "\t" row)))
      (recur (rest rows)))))

(defn print-computer-move [move]
  (println (str "Computer chose space " move)))

(defn show-error [message]
  (println message))

(defn declare-winner [marker]
  (println (str "Player " marker " wins!")))

(defn declare-draw []
  (println "Cats game"))

(defn get-user-input []
  (str/trim (read-line)))

(defn prompt-for-move [marker board]
  (println (str marker ": please enter move."))
  (get-user-input))

(defn show-marker-prompt [player-num]
  (println (str "Player " player-num ", please enter marker:")))


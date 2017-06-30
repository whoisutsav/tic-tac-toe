(ns tic-tac-toe.console 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-marker "_")

(defn- fill-board [markers board]
      (map #(get markers % empty-marker) board))

(defn print-board [markers board]
  (loop [rows (partition 3 (fill-board markers board))]
    (when-let [row (first rows)] 
      (println (reduce str "" (interpose "\t" row)))
      (recur (rest rows)))))

(defn show-message [message]
  (println message))

(defn declare-winner [marker]
  (println (str "Player " marker " wins!")))

(defn declare-draw []
  (println "Cats game"))

(defn show-move-prompt [current-marker markers board]
  (print-board markers board)
  (println (str current-marker ": please enter move.")))

(defn show-marker-prompt [player-num]
  (println (str "Player " player-num ", please enter marker:")))

(defn get-user-input []
  (str/trim (read-line)))

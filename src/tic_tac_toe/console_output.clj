(ns tic-tac-toe.console-output 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-marker "_")

(defn- fill-board [markers board]
      (map #(get markers % empty-marker) board))

(defn- format-row [row]
  (reduce str "" (interpose "\t" row)))

(defn print-board [markers board]
  (loop [rows (partition 3 (fill-board markers board))]
    (when-let [row (first rows)] 
      (println (format-row row))
      (recur (rest rows)))))

(defn print-message [message]
  (println message))

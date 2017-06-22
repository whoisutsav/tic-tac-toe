(ns tic-tac-toe.view 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-marker "_")

(defn- fill-board [marker1 marker2 board]
      (map #(case (board/player-at % board)
                1 marker1
                2 marker2
                empty-marker) (range 0 (count board))))

(defn- format-row [row]
  (reduce str "" (interpose "\t" row)))

(defn draw [marker1 marker2 board]
  (loop [rows (partition (board/axis-size board) (fill-board marker1 marker2 board))]
    (when-let [row (first rows)] 
      (println (format-row row))
      (recur (rest rows)))))

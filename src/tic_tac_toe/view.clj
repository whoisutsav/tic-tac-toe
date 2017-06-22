(ns tic-tac-toe.view 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.helper :as helper]))

(def empty-marker "_")

(defn str-format-row [rowv]
  (str/join "\t" (map #(if-not (nil? %1) %1 empty-marker) rowv)))

(defn str-format-board [board]
  (reduce #(str %1 (str-format-row %2) "\n") "" (map #(board/get-board-row % board) (range 0 (helper/axis-size board)))))

(defn print-board [board]
  (println (str-format-board board)))

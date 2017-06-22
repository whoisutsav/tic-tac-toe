(ns tic-tac-toe.board 
  (:require [tic-tac-toe.helper :as helper]))

(defn generate-empty-board [axis-size]
 (vec (repeat (* axis-size axis-size) nil)))

(defn get-board-row [row-num board]
  (let [size (helper/axis-size board)]
    (map #(nth board %) (range (* size row-num) (* size (inc row-num))))))

(defn get-board-rows [board]
  (map #(get-board-row % board) (range 0 (helper/axis-size board))))


(defn get-board-column [column-num board]
  (let [size (helper/axis-size board)]
    (map #(nth board %) (range column-num (count board) size))))

(defn get-board-columns [board]
 (map #(get-board-column % board) (range 0 (helper/axis-size board))))


(defn get-board-diagonal [diagonal-num board]
  (let [size (helper/axis-size board)]
    (if (= 0 diagonal-num)
      (map #(nth board %) (range 0 (count board) (inc size)))
      (map #(nth board %) (range (dec size) (dec (count board)) (dec size))))))

(defn get-board-diagonals [board]
 (map #(get-board-diagonal % board) (range 0 2)))

(defn apply-move [board move player-num]
  (assoc board move player-num))

(defn all-markers-same? [markerv]
  (and (not (nil? (first markerv))) (every? #(= (first markerv) %) markerv)))

(defn win? [board]
  (let [size (helper/axis-size board)]
  (or 
    (some all-markers-same? (get-board-rows board))
    (some all-markers-same? (get-board-columns board))
    (some all-markers-same? (get-board-diagonals board)))))

(defn is-occupied? [board cell] (not (nil? (nth board cell))))

(ns tic-tac-toe.core
  (:require  [clojure.math.numeric-tower :as math])
  (:require [clojure.string :as str])
  )

(def markers ["X" "O"])
(def empty-marker "_")

(defn prompt [question]
  (println question)
  (read-line)
  )

(defn other-marker [marker]
  (if (= marker (nth markers 0))
    (nth markers 1)
    (nth markers 0)
    )
  )

(defn axis-size [board]
  (math/sqrt (count board)) 
  )

(defn generate-empty-board [axis-size]
 (vec (repeat (* axis-size axis-size) nil)) 
  )

(defn get-board-row [row-num board]
  (let [size (axis-size board)]
    (map #(nth board %) (range (* size row-num) (* size (inc row-num))))
    )
  )

(defn get-board-column [column-num board]
  (let [size (axis-size board)]
    (map #(nth board %) (range column-num (count board) size)) 
    )
  )

(defn get-board-diagonal [diagonal-num board]
  (let [size (axis-size board)]
    (if (= 0 diagonal-num)
      (map #(nth board %) (range 0 (count board) (inc size)))
      (map #(nth board %) (range (dec size) (dec (count board)) (dec size)))
      )
    )
  )

(defn str-format-row [rowv]
  (str/join "\t" (map #(if-not (nil? %1) %1 empty-marker) rowv))
  )

(defn str-format-board [board]
  (reduce #(str %1 (str-format-row %2) "\n") "" (map #(get-board-row % board) (range 0 (axis-size board))))
         )

(defn validate-move [board move]
  (if (or (not (instance? Long move)) (< move 0) (>= move (count board)) (not= nil (nth board move))) 
    nil 
    move
    )
  )

(defn get-move [board marker]
  (or (println (str-format-board board)) (validate-move board (read-string (prompt (str marker "'s move: ")))) (get-move board marker))
  )

(defn apply-move [board move marker]
  (assoc board move marker)
  )

(defn all-markers-same? [markerv]
  (and (not (nil? (first markerv))) (every? #(= (first markerv) %) markerv))
  )

; TODO - change get-board-row to get-board-rows, get-board-column to columns, et al
; and move mapping into functions 
(defn win? [board]
  (let [size (axis-size board)]
  (or 
    (some all-markers-same? (map #(get-board-row % board) (range 0 size)))
    (some all-markers-same? (map #(get-board-column % board) (range 0 size)))
    (some all-markers-same? (map #(get-board-diagonal % board) (range 0 2)))
    )))

(defn game-turn [board marker]
  (if (win? board)
    (do (println (str-format-board board)) (println (str (other-marker marker) " WINS!")))
    (game-turn (apply-move board (get-move board marker) marker) (other-marker marker))
  )
)

(defn new-game [axis-size]
  (game-turn (generate-empty-board axis-size) (first markers)) 
  )

(defn -main
  [& args]
  (new-game 3))

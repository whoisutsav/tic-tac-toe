(ns tic-tac-toe.board
  (:require  [clojure.math.numeric-tower :as math])) 

(def empty-space :_)
(def mark1 :1)
(def mark2 :2)

(defn axis-size [board]
  (math/sqrt (count board)))

(defn- get-marker [player-num]
  (if (= 1 player-num) mark1 mark2))

(defn generate-empty-board [axis-size]
 (vec (repeat (* axis-size axis-size) empty-space)))

(defn apply-move [board cell player-num]
  (assoc board cell (get-marker player-num)))

(defn player-at [cell board] 
  (let [k (nth board cell)]
    (cond
      (= k mark1) 1
      (= k mark2) 2
      (= k empty-space) nil)))

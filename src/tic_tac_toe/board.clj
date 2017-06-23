(ns tic-tac-toe.board
  (:require  [clojure.math.numeric-tower :as math])) 

(def empty-space :_)
(def p1 :1)
(def p2 :2)
(def win-lines [[0 1 2] [3 4 5] [6 7 8]
                [0 3 6] [1 4 7] [2 5 8]
                [0 4 8] [2 4 6]])

(defn- get-marker [player-num]
  (if (= 1 player-num) p1 p2))

(defn generate-empty-board []
 (vec (repeat 9 empty-space)))

(defn apply-move [board cell player-num]
  (assoc board cell (get-marker player-num)))

(defn is-occupied? [cell board]
  (not= empty-space (nth board cell)))

(defn get-player [s]
  (cond
    (= p1 s) 1
    (= p2 s) 2
    :else nil))

(defn over? [board]
  (every? #(is-occupied? % board) 
          (range 0 (count board)))) 

(defn winner [board]
  (loop [remaining-lines win-lines]
    (let [line (map #(nth board %) (first remaining-lines))]
      (if (and (not (apply distinct? line)) (not= empty-space (first line)))
        (get-player (first line))
        (if (empty? (rest remaining-lines)) nil (recur (rest remaining-lines)))))))


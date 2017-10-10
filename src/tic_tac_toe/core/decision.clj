(ns tic-tac-toe.core.decision
  (:require [tic-tac-toe.core.board :as board]))

(defn- rows [size]
  (->> (range 1 (inc (* size size)))
       (partition size)))

(defn- columns [size]
  (apply map vector (rows size)))

(defn- diagonal [size]
  (range 1 (inc (* size size)) (inc size)))

(defn- reverse-diagonal [size]
  (range size (* size size) (dec size)))

(defn win-lines [board]
  (let [size (board/size board)]
   (map (fn [line] (map (fn [space] (board/get-marker space board)) line))
       (-> (rows size)
           (into (columns size))
           (conj (diagonal size))
           (conj (reverse-diagonal size))))))

(defn- full? [board]
  (empty? (board/empty-spaces board))) 

(defn winner [board]
  (loop [lines (win-lines board)]
    (if (empty? lines)
      nil
      (or (reduce #(if (= %1 %2) %1) (first lines)) (recur (rest lines))))))

(defn over? [board]
  (-> (or (full? board) (winner board))
      (boolean)))

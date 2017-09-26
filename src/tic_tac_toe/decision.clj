(ns tic-tac-toe.decision
  (:require [tic-tac-toe.board :as board]))

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

(defn winner [board]
  (loop [lines (win-lines board)]
    (if (empty? lines)
      nil
      (or (reduce #(if (= %1 %2) %1) (first lines)) (recur (rest lines))))))

(defn- no-more-moves? [board]
  (empty? (board/get-empty-spaces board))) 

(defn over? [board]
  (or (not= (winner board) nil) (no-more-moves? board)))

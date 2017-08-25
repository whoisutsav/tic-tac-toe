(ns tic-tac-toe.decision
  (:require [tic-tac-toe.board :as board]))

(defn diagonal [axis-size]
  (range 1 (inc (* axis-size axis-size)) (inc axis-size)))

(defn reverse-diagonal [axis-size]
  (range axis-size (* axis-size axis-size) (dec axis-size)))

(defn rows [axis-size]
  (->> (range 1 (inc (* axis-size axis-size)))
       (partition axis-size)))

(defn columns [axis-size]
  (apply map vector (rows axis-size)))

(defn win-lines [size]
  (let [axis-size (int (Math/sqrt size))]
    (-> (rows axis-size)
         (into (columns axis-size))
         (conj (diagonal axis-size))
         (conj (reverse-diagonal axis-size)))))

(def win-lines-memo (memoize win-lines))

(defn- no-more-moves? [board]
  (empty? (board/get-empty-spaces board))) 

(defn winner [board]
  (loop [lines (win-lines-memo (board/size board))]
    (when-first [line lines]
      (let [marks (map #(board/get-marker % board) line)
            first-mark (first marks)] 
        (if (and (not= nil first-mark) (every? #(= first-mark %) marks))
        first-mark 
        (recur (rest lines)))))))

(defn over? [board]
  (or (not= nil (winner board))
      (no-more-moves? board)))

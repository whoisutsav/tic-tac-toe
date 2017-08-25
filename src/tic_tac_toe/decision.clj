(ns tic-tac-toe.decision
  (:require [tic-tac-toe.board :as board]))

;(def win-lines [[1 2 3] [4 5 6] [7 8 9]
;                [1 4 7] [2 5 8] [3 6 9]
;                [1 5 9] [3 5 7]])

; [1 5 9] [1 6 11 16]
; [3 5 7] [4 7 10 13]

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
  (not-any? #(nil? (board/get-marker % board)) 
          (range 1 (inc (board/size board))))) 

(defn winner [board]
  (loop [lines (win-lines-memo (board/size board))]
    (when-first [line lines]
      (let [marks (map #(board/get-marker % board) line)
            first-mark (first marks)] 
        (if (and (not= nil first-mark) (every? #(= first-mark %) marks))
        first-mark 
        (recur (rest lines)))))))

(defn over? [board]
  (or (not= (winner board) nil)
      (no-more-moves? board)))

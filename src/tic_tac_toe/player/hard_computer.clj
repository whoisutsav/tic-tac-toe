(ns tic-tac-toe.player.hard-computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-ui :as console-ui]))

(defn leaf-node-value [board my-marker]
  (let [winner (decision/winner board)] 
      (cond 
        (nil? winner) 0
        (= winner my-marker) -1
        :else 1)))

(defn get-available-boards [marker board]
  (->> (board/get-empty-spaces board)
       (map #(board/put-marker board % marker)))) 

(defn get-other-marker [board marker]
  (->> (range 1 (inc (board/size board)))
       (map #(board/get-marker % board)) 
       (filter #(not (or (nil? %) (= marker %))))
       (first)))

(defn get-evaluation-fn [current-marker my-marker]
  (if (= current-marker my-marker)
    min
    max))

; do we need opponent marker? what if we just took it from board?
(defn max-loss [current-marker next-marker my-marker board]
  (if (decision/over? board)
    (leaf-node-value board my-marker)
    (let [evaluation-fn (get-evaluation-fn current-marker my-marker)
          max-loss-partial (partial max-loss next-marker current-marker my-marker)] 
      (->> (get-available-boards current-marker board)
           (map max-loss-partial)
           (flatten)
           (apply evaluation-fn)))))

; TODO refactor to be more declarative 
(defmethod get-move :hard-computer [board player]
  (let [marker (:marker player)
        other-marker (get-other-marker board marker)] 
    (loop [moves (board/get-empty-spaces board)
           min-max-loss nil
           best-move nil]
      (if (empty? moves)
        (do (console-ui/print-computer-move best-move) best-move)
        (let [max-loss-val (max-loss other-marker marker marker (board/put-marker board (first moves) marker))] 
          (if (or (nil? min-max-loss) (< max-loss-val min-max-loss))
            (recur (rest moves) max-loss-val (first moves))
            (recur (rest moves) max-loss-val best-move)))))))

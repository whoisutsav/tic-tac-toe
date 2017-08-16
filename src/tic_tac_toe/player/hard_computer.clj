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

(defn possible-boards [marker board]
  (->> (board/get-empty-spaces board)
       (map #(board/put-marker board % marker)))) 

; Is there a better way to get this? Should we pass in opponent-marker?
(defn other-marker [board marker]
  (->> (range 1 (inc (board/size board)))
       (map #(board/get-marker % board)) 
       (filter (comp not nil?))
       (filter #(not= marker %))
       (first)))

(defn evaluation-fn [current-marker my-marker]
  (if (= current-marker my-marker)
    min
    max))

(defn max-loss [current-marker next-marker my-marker board]
  (if (decision/over? board)
    (leaf-node-value board my-marker)
    (let [evaluation-fn (evaluation-fn current-marker my-marker)
          max-loss-partial (partial max-loss next-marker current-marker my-marker)] 
      (->> (possible-boards current-marker board)
           (map max-loss-partial)
           (flatten)
           (apply evaluation-fn)))))

; TODO try more declarative solution - use reduce
(defmethod get-move :hard-computer [board player]
  (let [marker (:marker player)
        other-marker (other-marker board marker)] 
    (loop [moves (board/get-empty-spaces board)
           min-max-loss nil
           best-move nil]
      (if (empty? moves)
        (do (console-ui/print-computer-move best-move) best-move)
        (let [move (first moves)
              new-board (board/put-marker board move marker)
              max-loss (max-loss other-marker marker marker new-board)] 
          (if (or (nil? min-max-loss) (< max-loss min-max-loss))
            (recur (rest moves) max-loss move)
            (recur (rest moves) min-max-loss best-move)))))))

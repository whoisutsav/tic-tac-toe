(ns tic-tac-toe.player.hard-computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-ui :as console-ui]))

(def max-depth 8)

(defn- leaf-node-value [board my-marker]
  (let [winner (decision/winner board)] 
      (cond 
        (nil? winner) 0
        (= winner my-marker) 1
        :else -1)))

(defn possible-boards [board marker]
  (->> (board/get-empty-spaces board)
       (map #(board/put-marker board marker %)))) 

(defn score [my-marker opponent-marker alpha beta depth board]
  (if (decision/over? board)
    (leaf-node-value board my-marker)
    (cond 
      (> depth max-depth) 0 
      (even? depth) ; maximizing player
        (loop [possible-boards (possible-boards board my-marker)
               alpha alpha 
               beta beta
               best-score Float/NEGATIVE_INFINITY]
          (if (empty? possible-boards) 
            best-score 
            (let [board (first possible-boards)
                  v (score my-marker opponent-marker alpha beta (inc depth) board)
                  alpha (max alpha v)
                  new-best-score (max best-score v)]
              (cond 
                (or (= 1 v) (<= beta alpha)) new-best-score 
                :else (recur (rest possible-boards) alpha beta new-best-score)))))
      :else ; minimizing player
        (loop [possible-boards (possible-boards board opponent-marker)
               alpha alpha
               beta beta
               best-score Float/POSITIVE_INFINITY]
          (if (empty? possible-boards)
            best-score
            (let [board (first possible-boards)
                  v (score my-marker opponent-marker alpha beta (inc depth) board)
                  beta (min beta v)
                  new-best-score (min best-score v)]
              (cond
                (or (= -1 beta) (<= beta alpha)) new-best-score 
                :else (recur (rest possible-boards) alpha beta new-best-score))))))))

(defn minimax-move [board my-marker opponent-marker]
  (loop [candidates (->> (board/get-empty-spaces board)
                         (map #(hash-map :move % :board (board/put-marker board my-marker %))))
         alpha Float/NEGATIVE_INFINITY 
         best-candidate nil]
    (if (empty? candidates)
      (:move best-candidate)
      (let [initial-depth 1
          candidate (first candidates)
          v (score my-marker opponent-marker alpha Float/POSITIVE_INFINITY initial-depth (:board candidate))
          alpha (max alpha v)]
        (cond 
          (= 1 v) (:move candidate)
          (or (nil? best-candidate) (> v (:score best-candidate))) (recur (rest candidates) alpha (assoc candidate :score v))
          :else (recur (rest candidates) alpha best-candidate))))))


(defmethod get-move :hard-computer [board player opponent]
  (let [my-marker (:marker player)
        opponent-marker (:marker opponent)]
    (minimax-move board my-marker opponent-marker)))

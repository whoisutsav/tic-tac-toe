(ns tic-tac-toe.player.hard-computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-ui :as console-ui]))

(defn- leaf-node-value [board my-marker]
  (let [winner (decision/winner board)] 
      (cond 
        (nil? winner) 0
        (= winner my-marker) 1
        :else -1)))

(defn possible-boards [board marker]
  (->> (board/get-empty-spaces board)
       (map #(board/put-marker board % marker)))) 

;(defn- active-marker [my-marker opponent-marker depth]
;  (if (even? depth)
;    my-marker
;    opponent-marker))

;(defn- evaluation-fn [depth]
;  (if (even? depth)
;    max
;    min))

;(defn score [my-marker opponent-marker depth board]
;  (if (decision/over? board)
;    (leaf-node-value board my-marker)
;    (let [score (partial score my-marker opponent-marker (inc depth))] 
;      (->> (active-marker my-marker opponent-marker depth)
;           (possible-boards board)
;           (map score)
;           (flatten)
;           (apply (evaluation-fn depth))))))

(defn score [my-marker opponent-marker alpha beta depth board]
  (if (decision/over? board)
    (leaf-node-value board my-marker)
    (if (even? depth) 
      ; maximizing player
      (loop [active-marker my-marker
             possible-boards (possible-boards board active-marker)
             alpha -9999 
             beta beta]
        (if (empty? possible-boards) 
          alpha 
          (let [board (first possible-boards)
                v (score my-marker opponent-marker alpha beta (inc depth) board)
                alpha (max alpha v)]
            (cond 
              (or (= 1 alpha) (<= beta alpha)) alpha 
              :else (recur active-marker (rest possible-boards) alpha beta)))))
      ; minimizing player
      (loop [active-marker opponent-marker
             possible-boards (possible-boards board active-marker)
             alpha alpha
             beta 9999]
        (if (empty? possible-boards)
          beta
          (let [board (first possible-boards)
                v (score my-marker opponent-marker alpha beta (inc depth) board)
                beta (min beta v)]
            (cond
              (or (= -1 beta) (<= beta alpha)) beta 
              :else (recur active-marker (rest possible-boards) alpha beta))))))))


(defn minimax-move [board my-marker opponent-marker]
  (let [initial-depth 1
        score (partial score my-marker opponent-marker -9999 9999 initial-depth)] 
    (->> (board/get-empty-spaces board)
         (map #(hash-map :move % :board (board/put-marker board % my-marker)))
         (map #(assoc % :score-value (score (:board %))))
         (apply max-key :score-value)
         (:move))))


(defmethod get-move :hard-computer [board player opponent]
  (let [my-marker (:marker player)
        opponent-marker (:marker opponent)]
    (minimax-move board my-marker opponent-marker)))

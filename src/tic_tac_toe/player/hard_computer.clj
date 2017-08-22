(ns tic-tac-toe.player.hard-computer
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console-ui :as console-ui]))

(defn- leaf-node-value [board my-marker]
  (let [winner (decision/winner board)] 
      (cond 
        (nil? winner) 0
        (= winner my-marker) -1
        :else 1)))

(defn possible-boards [board marker]
  (->> (board/get-empty-spaces board)
       (map #(board/put-marker board % marker)))) 

(defn- current-marker [my-marker opponent-marker depth]
  (if (even? depth)
    my-marker
    opponent-marker))

(defn- evaluation-fn [depth]
  (if (even? depth)
    min
    max))

(defn max-loss [my-marker opponent-marker depth board]
  (if (decision/over? board)
    (leaf-node-value board my-marker)
    (let [max-loss (partial max-loss my-marker opponent-marker (inc depth))] 
      (->> (current-marker my-marker opponent-marker depth)
           (possible-boards board)
           (map max-loss)
           (flatten)
           (apply (evaluation-fn depth))))))


(defn minimax-move [board my-marker opponent-marker]
  (let [max-loss (partial max-loss my-marker opponent-marker 1)] 
    (->> (board/get-empty-spaces board)
         (map #(hash-map :move % :board (board/put-marker board % my-marker)))
         (map #(assoc % :max-loss-value (max-loss (:board %))))
         (apply min-key :max-loss-value)
         (:move))))


(defmethod get-move :hard-computer [board player opponent]
  (let [my-marker (:marker player)
        opponent-marker (:marker opponent)
        move (minimax-move board my-marker opponent-marker)]
    (do (console-ui/print-computer-move move) move)))

(ns tic-tac-toe.web.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.player :as player]
            [clojure.data.json :as json]
            [ring.util.response :refer [response]]))

(defn map-response [{:keys [board current-player opponent-player] :as game}] 
  {"board" board 
   "marker" (:marker current-player)
   "opponent" (:type opponent-player)}) 

(defn ttt-response [ttt-response]
  (-> ttt-response
      (json/write-str)
      (response)))

(defn respond-game [game]
  (-> (map-response game)
      (assoc "status" "IN_PROGRESS")
      (ttt-response)))

(defn respond-win [game]
  (-> (map-response game)
      (assoc "status" "WIN")
      (ttt-response)))

(defn respond-draw [game]
  (-> (map-response game)
      (assoc "status" "DRAW")
      (ttt-response)))


(defn map-request [request]
  (let [params (:form-params request)]
    {
     :board (->> (get params "board")
                 (map keyword)
                 (vec))
     :current-player {:type :human-web :marker (keyword (get params "marker"))}
     :opponent-player {:type (keyword (get params "opponent")) :marker :O}
     :move (read-string (get params "move"))
     }
    ))

(defn- take-turn [{:keys [board current-player opponent-player] :as game}]
  (->> (player/get-move game) 
       (board/put-marker board (:marker current-player))
       (assoc {:current-player opponent-player :opponent-player current-player} :board))
  
  )

(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (respond-win game)
    (respond-draw game)
    ))

; TODO split into multiple functions 
(defn move [request]
  (loop [game (map-request request)]
    (let [updated-game (take-turn game)
          player-type (:type (:current-player updated-game))]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (if (or (= :computer player-type) (= :hard-computer player-type)) 
          (recur updated-game)
          (respond-game updated-game))))))

(defn new-game [request]
  (-> {:board (board/new-board 3)
       :marker "X"
       :opponent :hard-computer}
      (json/write-str)
      (response)))


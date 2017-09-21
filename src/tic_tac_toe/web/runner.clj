(ns tic-tac-toe.web.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.player :as player]
            [clojure.data.json :as json]
            [ring.util.response :refer [response]]))

(def IN_PROGRESS "IN_PROGRESS")
(def WIN "WIN")
(def DRAW "DRAW")

; TODO use get-in?
; TODO not quite "response" - be more clear with words
(defn assemble-body [params] 
  { 
    :status (:status params IN_PROGRESS)
    :board (:board params) 
    :marker (:marker (:current-player params))
    :opponent (:type (:opponent-player params))
    :winner (:winner params) 
    }) 

(defn flag-winner [params winner]
  (-> params 
      (assoc :winner winner)
      (assoc :status WIN))) 

(defn flag-draw [params]
  (assoc params :status DRAW))

; TODO change name
(defn ttt-response [params]
  (-> (assemble-body params)
      (json/write-str)
      (response)))

; TODO clean up 
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
    (-> game (flag-winner winner) (ttt-response))
    (-> game (flag-draw) (ttt-response))
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
          (ttt-response updated-game))))))

(defn new-game [request]
  (-> {:board (board/new-board 3)
       :marker "X"
       :opponent :hard-computer}
      (json/write-str)
      (response)))


package com.chandu.dsa.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class JobSequencingProblem {
    public static void main(String[] args) {
        Job[] jobs = { new Job(1, 2, 100), new Job(2, 1, 19),
                new Job(3, 2, 27), new Job(4, 1, 25),
                new Job(5, 3, 15)};
        printJobScheduling(jobs);
        System.out.println();
        printJobSchedulingUsingPriorityQueue(jobs);
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(n)
    private static void printJobSchedulingUsingPriorityQueue(Job[] jobs){
        Arrays.sort(jobs, (Job a, Job b) -> -(a.deadline - b.deadline));
        int maxDeadLine = getHighestDeadline(jobs);
        PriorityQueue<Job> pq = new PriorityQueue<>((Job a, Job b)-> -(a.profit - b.profit));
        Job temp;
        int maxProfit = 0;
        List<Integer> list = new ArrayList<>();

        for (int i=maxDeadLine, j=0; i>=1; i--){
            for ( ; j< jobs.length; j++){
                if(jobs[j].deadline == i)
                    pq.add(jobs[j]);
                else if (jobs[j].deadline < i)
                    break;
            }
            if(!pq.isEmpty()){
                temp = pq.poll();
                list.add(temp.id);
                maxProfit += temp.profit;
            }
        }

        System.out.println("Sequence of jobs is: ");
        for(int i=list.size()-1; i>=0; i--)
            System.out.print(list.get(i) + " ");
        System.out.println();
        System.out.println("Maximum profit is: " + maxProfit);
    }

    //Time Complexity: O(n ^ 2)
    //Space Complexity: O(n)
    private static void printJobScheduling(Job[] jobs){
        int maxDeadLine = getHighestDeadline(jobs);
        Arrays.sort(jobs, (Job a, Job b)-> - (a.profit - b.profit));
        int[] result = new int[maxDeadLine];
        int maxProfit = 0;
        for (Job job : jobs){
            for (int j= job.deadline-1; j>=0; j--){
                if (result[j] == 0){
                    result[j] = job.id;
                    maxProfit += job.profit;
                    break;
                }
            }
        }
        System.out.println("Maximum profit is: " + maxProfit);
        System.out.println("Sequence of jobs is : ");
        for (int i : result){
            if (i>0)
                System.out.print(i + " ");
        }
    }

    private static int getHighestDeadline(Job[] jobs){
        int maxDeadLine = Integer.MIN_VALUE;
        for (Job j : jobs)
            maxDeadLine = Integer.max(maxDeadLine, j.deadline);
        return maxDeadLine;
    }

    static class Job{
        int id;
        int profit;
        int deadline;

        Job(int id, int deadLine, int profit){
            this.id = id;
            this.profit = profit;
            this.deadline = deadLine;
        }
    }
}

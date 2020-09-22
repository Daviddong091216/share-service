package org.launchcode.shareservice.models;

import java.util.ArrayList;

public class AcNeedsRepairingData {
    /**
     * Returns the results of searching the AcNeedsRepairings data by field and search term.
     *
     * @param column  AcNeedsRepairing field that should be searched.
     * @param value   Value of the field to search for.
     * @param allAcNeedsRepairings The list of AcNeedsRepairings to search.
     * @return List of all AcNeedsRepairings matching the criteria.
     */
    //make the method static because we do not need to create an instance of the class.
    public static ArrayList<AcNeedsRepairing> findByColumnAndValue(String column, String value, Iterable<AcNeedsRepairing> allAcNeedsRepairings) {

        ArrayList<AcNeedsRepairing> results = new ArrayList<>();

        if (value.toLowerCase().equals("all") || value.equals("")) {
            return (ArrayList<AcNeedsRepairing>) allAcNeedsRepairings;
        }

        if (column.equals("all")) {
            results = findByValue(value, allAcNeedsRepairings);
            return results;
        }
        for (AcNeedsRepairing job : allAcNeedsRepairings) {

            String aValue = getFieldValue(job, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(job);
            }
        }

        return results;
    }

    public static String getFieldValue(AcNeedsRepairing job, String fieldName) {
        String theValue;
        if (fieldName.equals("name")) {
            theValue = job.getName();
        } else if (fieldName.equals("problem")) {
            theValue = job.getProblems().toString();
        } else if (fieldName.equals("state")) {
            theValue = job.getState().toString();
        } else {
            theValue = job.getZipCode().toString();
        }

        return theValue;
    }

    /**
     * Search all Job fields for the given term.
     *
     * @param value   The search term to look for.
     * @param allAcNeedsRepairings The list of jobs to search.
     * @return List of all jobs with at least one field containing the value.
     */
    public static ArrayList<AcNeedsRepairing> findByValue(String value, Iterable<AcNeedsRepairing> allAcNeedsRepairings) {
        String lower_val = value.toLowerCase();

        ArrayList<AcNeedsRepairing> results = new ArrayList<>();

        for (AcNeedsRepairing job : allAcNeedsRepairings) {

            if (job.getName().toLowerCase().contains(lower_val)) {
                results.add(job);
            } else if (job.getProblems().toString().toLowerCase().contains(lower_val)) {
                results.add(job);
            } else if (job.getState().toString().toLowerCase().contains(lower_val)) {
                results.add(job);
            } else if (job.getZipCode().toString().toLowerCase().contains(lower_val)) {
                results.add(job);
            } else if (job.toString().toLowerCase().contains(lower_val)) {
                results.add(job);
            }

        }

        return results;
    }
}

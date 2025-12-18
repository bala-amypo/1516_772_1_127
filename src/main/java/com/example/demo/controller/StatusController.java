@PutMapping("/{id}")
public Complaint updateStatus(@PathVariable Long id,
                              @RequestParam Complaint.Status status) {
    return complaintStatusService.updateStatus(id, status);
}

package CampusServiceManagementSystem;

/**
 * ============================================================================
 * PILLAR 2: INHERITANCE & PILLAR 3: POLYMORPHISM
 * ============================================================================
 * Represents a Document Request service (e.g., Certificate of Enrollment,
 * Good Moral Certificate, Transcript Request).
 * 
 * - Inherits common service fields from CampusService.
 * - Adds specific field: numberOfPages.
 * - Overrides calculatePrice(): Base Price + (numberOfPages * 5).
 * ============================================================================
 */
public class DocumentRequest extends CampusService {

    private String documentType;
    private int numberOfPages;
    private static final double PRICE_PER_PAGE = 5.0;

    public DocumentRequest(String serviceId, String documentType, int numberOfPages, double basePrice) {
        super(serviceId, "Document Request (" + documentType + ")", basePrice);
        this.documentType = documentType;
        this.numberOfPages = Math.max(1, numberOfPages);
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = Math.max(1, numberOfPages);
    }

    // ------------------------------------------------------------------------
    // [POLYMORPHISM] Overriding calculatePrice()
    // Formula: Base Price + (numberOfPages * 5)
    // ------------------------------------------------------------------------
    @Override
    public double calculatePrice() {
        return getBasePrice() + (numberOfPages * PRICE_PER_PAGE);
    }

    public double getAdditionalPagesFee() {
        return numberOfPages * PRICE_PER_PAGE;
    }

    @Override
    public void printServiceSummary() {
        System.out.println("Service: " + documentType);
        System.out.println("Pages: " + numberOfPages);
        System.out.printf("Base Price: ₱%,.2f%n", getBasePrice());
        System.out.printf("Additional Pages: ₱%,.2f%n", getAdditionalPagesFee());
        System.out.printf("Total Price: ₱%,.2f%n", calculatePrice());
    }
}

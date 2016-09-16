package net.ponder2.managedobject;

import java.util.HashMap;
import java.util.Map;

import net.ponder2.objects.P2Object;
import net.ponder2.exception.Ponder2Exception;

/**
 * Adaptor object for managed object
 * 
 * @author Auto generated by annotation processor tool
 */
public class XSSFilterP2Adaptor extends net.ponder2.P2ObjectAdaptor {

  /**
   * The map of static operations
   */
  private final static Map<String, StaticOperation> staticop;
  /**
   * The map of create operations to constructors
   */
  private final static Map<String, CreateOperation> create;
  /**
   * The map of instance operations to methods
   */
  private final static Map<String, InstanceOperation> operation;

  // Create the call tables when the class is loaded
  static {
    staticop = new HashMap<String, StaticOperation>();
    create = new HashMap<String, CreateOperation>();
    operation = new HashMap<String, InstanceOperation>();

    // Create operation 'create' calls constructor for XSSFilter
    create.put("create", new CreateOperation() {
      @Override
      public net.ponder2.ManagedObject call(P2Object obj, P2Object source, String operation, P2Object... args)
          throws net.ponder2.exception.Ponder2Exception {
        return new XSSFilter();
      }
    });

    // Operation 'user:filter:' calls filterUserText
    operation.put("user:filter:", new InstanceOperation() {
      @Override
      public P2Object call(P2Object thisObj, net.ponder2.ManagedObject obj, P2Object source, String operation, P2Object... args)
          throws net.ponder2.exception.Ponder2Exception {
        java.lang.String value = 
             ((XSSFilter)obj).filterUserText(args[0].asString(), args[1].asString());
        return P2Object.create(value);
      }
    });
  }

  public XSSFilterP2Adaptor() {
  }

  public XSSFilterP2Adaptor(P2Object source, String operation, P2Object... args)
      throws Ponder2Exception {
     super(source, operation, args);
  }


  @Override
  public void getCreateOrStaticOperation(CreateOrStaticOperation opInfo) throws net.ponder2.exception.Ponder2OperationException {
    boolean found = opInfo.findOp(create,staticop);
    if (!found) super.getCreateOrStaticOperation(opInfo);
  }


  @Override
  public CreateOperation getCreateOperation(String opName) throws net.ponder2.exception.Ponder2OperationException {
    CreateOperation op = create.get(opName);
    return op != null ? op : super.getCreateOperation(opName);
  }

  @Override
  public InstanceOperation getInstanceOperation(String opName) throws net.ponder2.exception.Ponder2OperationException {
    InstanceOperation op = operation.get(opName);
    return op != null ? op : super.getInstanceOperation(opName);
  }

}

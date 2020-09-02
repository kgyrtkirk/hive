<?php
namespace metastore;

/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TBinaryProtocolAccelerated;
use Thrift\Exception\TApplicationException;

class MetadataPpdResult
{
    static public $isValidate = false;

    static public $_TSPEC = array(
        1 => array(
            'var' => 'metadata',
            'isRequired' => false,
            'type' => TType::STRING,
        ),
        2 => array(
            'var' => 'includeBitset',
            'isRequired' => false,
            'type' => TType::STRING,
        ),
    );

    /**
     * @var string
     */
    public $metadata = null;
    /**
     * @var string
     */
    public $includeBitset = null;

    public function __construct($vals = null)
    {
        if (is_array($vals)) {
            if (isset($vals['metadata'])) {
                $this->metadata = $vals['metadata'];
            }
            if (isset($vals['includeBitset'])) {
                $this->includeBitset = $vals['includeBitset'];
            }
        }
    }

    public function getName()
    {
        return 'MetadataPpdResult';
    }


    public function read($input)
    {
        $xfer = 0;
        $fname = null;
        $ftype = 0;
        $fid = 0;
        $xfer += $input->readStructBegin($fname);
        while (true) {
            $xfer += $input->readFieldBegin($fname, $ftype, $fid);
            if ($ftype == TType::STOP) {
                break;
            }
            switch ($fid) {
                case 1:
                    if ($ftype == TType::STRING) {
                        $xfer += $input->readString($this->metadata);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                case 2:
                    if ($ftype == TType::STRING) {
                        $xfer += $input->readString($this->includeBitset);
                    } else {
                        $xfer += $input->skip($ftype);
                    }
                    break;
                default:
                    $xfer += $input->skip($ftype);
                    break;
            }
            $xfer += $input->readFieldEnd();
        }
        $xfer += $input->readStructEnd();
        return $xfer;
    }

    public function write($output)
    {
        $xfer = 0;
        $xfer += $output->writeStructBegin('MetadataPpdResult');
        if ($this->metadata !== null) {
            $xfer += $output->writeFieldBegin('metadata', TType::STRING, 1);
            $xfer += $output->writeString($this->metadata);
            $xfer += $output->writeFieldEnd();
        }
        if ($this->includeBitset !== null) {
            $xfer += $output->writeFieldBegin('includeBitset', TType::STRING, 2);
            $xfer += $output->writeString($this->includeBitset);
            $xfer += $output->writeFieldEnd();
        }
        $xfer += $output->writeFieldStop();
        $xfer += $output->writeStructEnd();
        return $xfer;
    }
}
